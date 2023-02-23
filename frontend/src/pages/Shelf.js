import React from 'react';
import classes from "./Shelf.module.css";
import AddGame from "../components/Games/AddGame";
import MyGameList from "../services/MyGameList";
import BorrowedGameList from "../services/BorrowedGamesList";




function Shelf() {
    return (
      <div className={classes.games}>
          <AddGame/>
          <MyGameList/>
          <BorrowedGameList/>
      </div>
    );
}

export default Shelf