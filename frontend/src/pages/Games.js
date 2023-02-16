import React from 'react';
import GameService from "../services/GameService";
import classes from "./Games.module.css"



function Games() {
    return (
      <div className={classes.games}>
          <GameService/>
      </div>
    );
}

export default Games