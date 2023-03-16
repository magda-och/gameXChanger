import React from 'react';
import classes from "./Shelf.module.css";
import MyGameList from "../components/Games/MyGameList";
import BorrowedGameList from "../components/Games/BorrowedGamesList";
import GamesSearchingBar from "../components/Games/GamesSearchingBar";




function Shelf() {
    return (
      <div className={classes.games} style={{alignItems:"center"}}>
          <GamesSearchingBar />
          <MyGameList/>
         {/* <BorrowedGameList/>*/}

      </div>
    );
}

export default Shelf