import SideNav, {Toggle, NavItem, NavIcon, NavText} from '@trendmicro/react-sidenav'

import '@trendmicro/react-sidenav/dist/react-sidenav.css'
import classes from "./LeftSidebar.module.css";
import {useNavigate} from "react-router-dom";

function LeftSidebar (){
const navigate = useNavigate();
    return <SideNav style={{'backgroundColor': '#975C8D' }}
    onSelect={selected=> {
    console.log(selected)
        navigate('/'+ selected)
    }}
    className = {classes.leftSidebar}
    >
    <SideNav.Toggle />
    <SideNav.Nav  defaultSelected = "profile" style={{'backgroundColor': '#975C8D'}}>
        <NavItem eventKey={"profile/shelf"} id = "nav-id" >
            <NavIcon>  <i className='fa fa-fw fa-solid fa-gamepad' style={{fontSize: "2em", color: "white"}}></i> </NavIcon>
            <NavText style = {{ fontSize : "1.3em", fontFamily:'Arial Narrow', color: "white"}}>My shelf</NavText>
        </NavItem>
        <NavItem eventKey={"profile"} id = "nav-id">
            <NavIcon><i className='fa fa-fw fa-solid fa-circle-user' style={{fontSize: "2em", color: "white"}}></i> </NavIcon>
            <NavText style = {{ fontSize : "1.3em", fontFamily:'Arial Narrow', color: "white"}}>Profile</NavText>
        </NavItem>
        <NavItem eventKey={"profile/friends"} id = "nav-id">
            <NavIcon>  <i className='fa fa-fw fa-solid fa-users' style={{fontSize: "2em", color: "white"}}></i> </NavIcon>
            <NavText style = {{ fontSize : "1.3em", fontFamily:'Arial Narrow', color: "white"}}>Friends</NavText>
        </NavItem>
        <NavItem eventKey={"profile/invitations"} id = "nav-id">
            <NavIcon><i className='fa fa-fw fa-envelope' style={{fontSize: "2em", color: "white"}}></i> </NavIcon>
            <NavText style = {{ fontSize : "1.3em", fontFamily:'Arial Narrow', color: "white"}}>Invitations</NavText>
        </NavItem>
        <NavItem eventKey={""} id = "nav-id">
            <NavIcon><i className='fa fa-fw fa-solid fa-home' style={{fontSize: "2em", color: "white"}}></i> </NavIcon>
            <NavText style = {{ fontSize : "1.3em", fontFamily:'Arial Narrow', color: "white"}}>Home</NavText>
        </NavItem>
    </SideNav.Nav></SideNav>
}
export default LeftSidebar;