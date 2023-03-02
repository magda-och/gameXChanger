import React from 'react';
import classes from "./Shelf.module.css";
import AddGame from "../components/Games/AddGame";
import MyGameList from "../services/MyGameList";
import BorrowedGameList from "../services/BorrowedGamesList";
import GamesSearchingBar from "../components/Games/GamesSearchingBar";




function Shelf() {
    return (
      <div className={classes.games}>
          <AddGame/>
          <MyGameList/>
         {/* <BorrowedGameList/>*/}
          <GamesSearchingBar />
      </div>
    );
}

export default Shelf