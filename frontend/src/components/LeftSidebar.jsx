import SideNav, {Toggle, NavItem, NavIcon, NavText} from '@trendmicro/react-sidenav'

import '@trendmicro/react-sidenav/dist/react-sidenav.css'
import classes from "./LeftSidebar.module.css";
import {useNavigate} from "react-router-dom";

function LeftSidebar (){
const navigate = useNavigate();
    return <SideNav
    onSelect={selected=> {
    console.log(selected)
        navigate('/'+ selected)
    }}
    className = {classes.leftSidebar}
    >
    <SideNav.Toggle />
    <SideNav.Nav  defaulfSelected = "profile">
        <NavItem eventKey={"profile/games"}>
            <NavIcon>  <i className='fa fa-fw fa-solid fa-gamepad' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>My shelf</NavText>
        </NavItem>
        <NavItem eventKey={"profile"}>
            <NavIcon><i className='fa fa-fw fa-solid fa-circle-user' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Profile</NavText>
        </NavItem>
        <NavItem eventKey={"profile/friends"}>
            <NavIcon>  <i className='fa fa-fw fa-solid fa-users' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Friends</NavText>
        </NavItem>
        <NavItem eventKey={"profile/invitations"}>
            <NavIcon><i className='fa fa-fw fa-envelope' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Invitations</NavText>
        </NavItem>
        <NavItem eventKey={""}>
            <NavIcon><i className='fa fa-fw fa-solid fa-home' style={{fontSize: "1.5em"}}></i> </NavIcon>
            <NavText>Home</NavText>
        </NavItem>
    </SideNav.Nav></SideNav>
}
export default LeftSidebar;