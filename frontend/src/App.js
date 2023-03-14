import './App.css';
import {
    createBrowserRouter,
    RouterProvider
} from "react-router-dom";
import MainLayout from "./components/MainLayout/MainLayout";
import Home from "./pages/home/Home";
import About from "./pages/aboutUs/About";
import Contacts from "./pages/Contacts";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import Invitations from "./pages/Invitations";
import ProfileLayout from "./components/ProfileLayout/ProfileLayout";
import Shelf from "./pages/Shelf";
import Friends from "./components/Friends/Friends";
import Registration from "./pages/Registration";
import FriendGames from "./components/Games/FriendGames";
import LendGame from "./components/Games/LendGame";
import UserList from "./components/Users/UserList";

const router = createBrowserRouter([
    {
        path: '/',
        element: <MainLayout/>,
        children: [
            {index: true, element: <Home/>},
            {path: '/about', element: <About/>},
            {path: '/login', element: <Login/>},
            {path: '/contacts', element: <Contacts/>},
            {path: '/register', element: <Registration/>},

        ],
    },
    {
        path: '/profile',
        element: <ProfileLayout/>,
        children: [
            {path: '/profile', element: <Profile/>},
            {path: '/profile/shelf', element: <Shelf/>},
            {
                path: 'profile/lend/:id',
                element: <LendGame/>
            },
            {path: '/profile/friends', element: <Friends/>},
            {path: '/profile/invitations', element: <Invitations/>},
            {
                path: '/profile/games/:id',
                element: <FriendGames/>
            },
            {
                path: '/admin',
                element: <UserList/>
            }
        ],
    },

]);

function App() {
    return <RouterProvider router={router}/>;
}

export default App;
