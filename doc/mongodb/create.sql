use record;
db.createCollection("user");
db.user.createIndex({userName:1},{unique:true});