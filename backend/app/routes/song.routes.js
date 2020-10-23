module.exports = app => {
    const songs = require("../controllers/song.controller.js");
  
    var router = require("express").Router();
  
    // Create a new Songs
    router.post("/", songs.create);
  
    // Retrieve all Songs
    router.get("/", songs.findAll);
  
    // Retrieve a single Song with id
    router.get("/:id", songs.findOne);
  
    // Update a Song with id
    router.put("/:id", songs.update);
  
    // Delete a Song with id
    router.delete("/:id", songs.delete);

    app.use('/api/songs', router);
  };