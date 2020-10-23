module.exports = (sequelize, Sequelize) => {
    const Song = sequelize.define("song", {
      name: {
        type: Sequelize.STRING
      },
      artist: {
        type: Sequelize.STRING
      },
      lenght: {
        type: Sequelize.STRING
      },
      year: {
        type: Sequelize.STRING
      }
    }, {timestamps: false});
  
    return Song;
  };