module.exports = {
    HOST: "localhost",
    USER: "root",
    PASSWORD: "e29666373",
    DB: "db_songs",
    dialect: "mysql",
    pool: {
      max: 5,
      min: 0,
      acquire: 30000,
      idle: 10000
    }
  };