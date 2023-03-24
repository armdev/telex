rs.status();
db.createUser({user: 'admin', pwd: 'admin', roles: [ { role: 'root', db: 'admin' } ]})

use admin;

db.createUser(
  {
    user: "uber",
    pwd: "uber123",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
);
