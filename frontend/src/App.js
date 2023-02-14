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
import LeftSidebar from "./components/LeftSidebar";
import Profile from "./pages/Profile";
import ProfileLayout from "./components/ProfileLayout";

const router = createBrowserRouter([
    {
        path: '/',
        element: <MainLayout/>,
        children: [
            {index: true, element: <Home/>},
            {path: '/about', element: <About/>},
            {path: '/user/login', element: <Login/>},
            {path: '/contacts', element: <Contacts/>}
        ],
    }, {
    path: '/profile',
        element: <ProfileLayout/>,
        children: [
            {index: '/', element: <Home/>},
            {index: this, element: <Profile/>},
            // {index: '/', element: <Shelf/>},
            // {index: '/', element: <Borrowed/>},
            // {index: '/', element: <Friends/>},
    ]}
]);

function App() {
    return <RouterProvider router={router}/>;
}

export default App;
