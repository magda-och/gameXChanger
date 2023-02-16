import React from 'react';
import GameService from "../services/GameService";
import classes from "./Games.module.css"
import AddGame from "./games/AddGame";



function Games() {
    return (
      <div className={classes.games}>
          <GameService/>
          <AddGame/>
      </div>
    );
}

export default Games