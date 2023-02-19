import React from 'react';
import classes from "../styles/Games.module.css"
import AddGame from "../components/Games/AddGame";
import GameService from "../services/GameService";
import BorrowedGameList from "../services/BorrowedGamesList";
import AddGameButton from "../components/Games/AddGameButton";



function Shelf() {
    return (
      <div className={classes.games}>
          <AddGameButton title={"Add new game"}/>
          <GameService/>
          <BorrowedGameList/>
      </div>
    );
}

export default Shelf