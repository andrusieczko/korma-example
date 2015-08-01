(ns korma-example
  (:require [korma.db :refer [postgres]]
            [korma.core :refer [defentity pk with where create-entity database entity-fields select many-to-many many-to-many-fn]]))

;; This is the example that works fine with Korma 0.4.2:

;(def pg-connection (get-db (get-config))) ; need to have one config defined once
;
;(declare users roles user_roles)
;
;(defentity users
;           (database pg-connection)
;           (entity-fields :id :username)
;           (many-to-many roles :user_roles {:lfk "user_id", :rfk "role"}))
;(defentity user_roles
;           (database pg-connection)
;           (entity-fields :user_id :role_id))
;(defentity roles
;           (database pg-connection)
;           (entity-fields :role :description)
;           (pk :role))

;; The following won't work:

(defn- get-user-roles
  [db]
  (-> (create-entity "user_roles")
      (database db)
      (entity-fields :user_id :role_id)))

(defn- get-users
  [db roles-entity user-roles-entity]
  (-> (create-entity "users")
      (database db)
      (entity-fields :id :username)
      (many-to-many-fn roles-entity user-roles-entity {:lfk "user_id", :rfk "role"})))

(defn- get-roles
  [db]
  (-> (create-entity "roles")
      (database db)
      (entity-fields :role :description)
      (pk :role)))

(defn start [& [{db :db-spec}]]
  (let [roles-entity (get-roles db)
        user-roles-entity (get-user-roles db)
        users-entity (get-users db roles-entity user-roles-entity)
        users-results (select users-entity (with roles-entity (where {:role :user_roles.role_id})))]
    (prn users-results)))

(defn- get-config
  []
  {
   :db       "eyeota-ui"
   :user     "postgres"
   :password "andrut"
   :host     "localhost"
   :port     "5432"
   })

(defn system
  [& args]
  {
   :db-spec (postgres (get-config))
   })

(defn -main
  [& args]
  (start (system)))