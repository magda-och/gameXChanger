import SideNav, {Toggle, NavItem, NavIcon, NavText} from '@trendmicro/react-sidenav'

import '@trendmicro/react-sidenav/dist/react-sidenav.css'
import classes from "./MainLayout.module.css";
import {useNavigate, useNavigation} from "react-router-dom";

function LeftSidebar (){

    return <SideNav
    onSelect={selected=> {
    console.log(selected)
        navigate('/'+ selected)
    }}
    className = {classes.leftSidebar}
    >
    <SideNav.Toggle />
    <SideNav.Nav  defaulfSelected = "home">
        <NavItem eventKey={"home"}>
            <NavIcon><i className='fa fa-fw fa-home' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Home</NavText>
            <navLink></navLink>
        </NavItem>
        <NavItem>
            <NavIcon><i className='fa fa-fw fa-solid fa-user' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Profile</NavText>
        </NavItem>
        <NavItem>
            <NavIcon>  <i className='fa fa-fw fa-solid fa-gamepad' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>My shelf</NavText>
        </NavItem>
        <NavItem>
            <NavIcon>  <i className='fa fa-fw fa-solid fa-download' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Borrowed games</NavText>
        </NavItem>
        <NavItem>
            <NavIcon>  <i className='fa fa-fw fa-solid fa-poo' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Friends</NavText>
        </NavItem>
    </SideNav.Nav></SideNav>
}
export default LeftSidebar;