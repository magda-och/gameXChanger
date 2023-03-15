import React from "react";
import classes from "./Invitations.module.css"
import {InvitationAPI} from "../api/InvitationAPI";
import AuthenticationService from "../services/AuthenticationService";

const userId = AuthenticationService.getLoggedInUserID();
class Invitations extends React.Component{

    constructor(props) {
        super(props);
        this.state= {
            sendInvitations: [],
            receivedInvitations:[]
        }
    }


    componentDidMount() {
        InvitationAPI.getSend(userId).then(
            (response) => {
                //this.state.invitations = response.data
                this.setState({ sendInvitations: [{
                        requestFriendId: 1,
                        requestStatus: 1,
                        fromUserId: 1,
                        toUserId: 1,
                        message:1
                    }] });
                this.setState({ sendInvitations:response.data });
                //console.log(this.state)
        });

        InvitationAPI.getReceived(userId).then(
            (response) => {
                this.setState({ receivedInvitations: [{
                        requestFriendId: 1,
                        requestStatus: 1,
                        fromUserId: 1,
                        toUserId: 1,
                        message:1
                    }] });
                this.setState({ receivedInvitations:response.data });
            });
    }

    cancelInvitation(id, e){
        InvitationAPI.delete(id)
            .then(res => {
                alert("You deleted invitation")
                console.log(res);
                const sendInvitations = this.state.sendInvitations.filter(item => item.requestFriendId !== id);
                this.setState({ sendInvitations });
            })
    }

    updateInvitationStatus(id, status, e){
        console.log("cos");
        InvitationAPI.update(id, status)
            .then(res =>{
                console.log(res);
                if(status==="ACCEPTED"){
                    alert("You added friend!")
                } else {
                    alert("You reject user")
                }
                InvitationAPI.getReceived(userId).then(
                    (response) => {
                        this.setState({ receivedInvitations:response.data});
                    });
                InvitationAPI.getSend(userId).then(
                    (response) => {
                        this.setState({ sendInvitations:response.data});
                    });
            });
    }

    render() {
        return(
            <div className={classes.invitations}>
                <div>
                <h1 className ="text-center"> Received Invitations</h1>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td> Invitation state</td>
                        <td> Invitation from</td>
                        <td> Invitation to</td>
                        <td> Invitation message</td>

                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.receivedInvitations.map (
                            invitation => {
                                const visibility = invitation.requestStatus === "WAITING" ? classes.visible : classes.hidden
                                return <tr key={invitation.requestFriendId}>
                                    <td>{invitation.requestStatus}</td>
                                    <td>{invitation.fromUserId.firstName+ " "+ invitation.fromUserId.lastName}</td>
                                    <td>{invitation.toUserId.firstName+ " "+ invitation.toUserId.lastName}</td>
                                    <td>{invitation.message}</td>
                                    <td><button style={{background:"#443C68", border:"none", color:"white"}} className={"btn btn-success " + visibility} onClick={(e) => this.updateInvitationStatus(invitation.requestFriendId,"ACCEPTED", e)}>Accept</button></td>
                                    <td><button style={{background:"#443C68", border:"none", color:"white"}} className={"btn btn-danger " + visibility} onClick={(e) => this.updateInvitationStatus(invitation.requestFriendId,"REJECTED", e)}>Reject</button></td>
                                </tr>
                            }
                        )
                    }
                    </tbody>
                </table>
                </div>
                <div>
                    <h1 className ="text-center"> Sent invitations</h1>
                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <td> Invitation state</td>
                            <td> Invitation from</td>
                            <td> Invitation to</td>
                            <td> Invitation message</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.sendInvitations.map(
                                invitation => {
                                    return <tr key={invitation.id}>
                                        <td>{invitation.requestStatus}</td>
                                        <td>{invitation.fromUserId.firstName+ " " + invitation.fromUserId.lastName}</td>
                                        <td>{invitation.toUserId.firstName+ " "+ invitation.toUserId.lastName}</td>
                                        <td>{invitation.message}</td>
                                        <td><button style={{background:"#443C68", border:"none", color:"white"}}  className="btn btn-secondary" onClick={(e) => this.cancelInvitation(invitation.requestFriendId, e)}>Cancel</button></td>
                                    </tr>
                                }
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
export default Invitations