import './App.css';
import {
    createBrowserRouter,
    RouterProvider
} from "react-router-dom";
import MainLayout from "./components/MainLayout";
import Home from "./pages/Home";
import About from "./pages/About";
import Contacts from "./pages/Contacts";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import Invitations from "./pages/Invitations";
import ProfileLayout from "./components/ProfileLayout";
import Games from "./pages/Games";

const router = createBrowserRouter([
    {
        path: '/',
        element: <MainLayout/>,
        children: [
            {index: true, element: <Home/>},
            {path: '/about', element: <About/>},
            {path: '/user/login', element: <Login/>},
            {path: '/contacts', element: <Contacts/>},

        ],
    },
    {
        path: '/profile',
        element: <ProfileLayout/>,
        children: [
            {path: this, element: <Profile/>},
            {path: '/profile/games', element: <Games/>},
            //{index: '/borrowedGames', element: <Borrowed/>},
           // {index: '/profile/friends', element: <Friends/>},
            {path: '/profile/invitations', element: <Invitations/>},
        ],
    },
]);

function App() {
    return <RouterProvider router={router}/>;
}

export default App;
