import React from 'react';
import classes from "./Shelf.module.css";
import MyGameList from "../services/MyGameList";
import BorrowedGameList from "../services/BorrowedGamesList";
import GamesSearchingBar from "../components/Games/GamesSearchingBar";




function Shelf() {
    return (
      <div className={classes.games}>
          <MyGameList/>
         {/* <BorrowedGameList/>*/}
          {/*<GamesSearchingBar />*/}
      </div>
    );
}

export default Shelf