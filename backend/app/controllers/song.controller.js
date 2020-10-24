const db = require("../models");
const Song = db.songs;
const Op = db.Sequelize.Op;

// Create and Save a new Song
// req --> request (contains the body)
exports.create = (req, res) => {
  // Validate request
  if (!req.body.name || !req.body.artist || !req.body.lenght || !req.body.year) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Song
  const song = {
    id: req.body.id,
    name: req.body.name,
    artist: req.body.artist,
    lenght: req.body.lenght,
    year: req.body.year
  };

  // Save Song in the database
  Song.create(song)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Song."
      });
    });
};

// Retrieve all Songs from the database.
exports.findAll = (req, res) => {
  
    Song.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving songs."
        });
      });
};

// Find a single Song with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  Song.findByPk(id)
    .then(data => {
      if(!data){
        res.status(400).send({
          message:
            "No Song found with that id"
        })
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message);
      console.log("hola");
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving song with id"
      });
      return;
    });
};

// Update a Song by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Song.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Song was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update Song with id=${id}. Maybe Song was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating Song with id=" + id
      });
    });
};

// Delete a Song with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Song.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Song was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete Song with id=${id}. Maybe Song
           was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete Song with id=" + id
      });
    });
};
