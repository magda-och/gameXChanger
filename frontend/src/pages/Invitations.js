import React, {useState} from "react";
import InvitationService from "../services/InvitationService";
import classes from "./Invitations.module.css"
import axios from "axios";

const API_URL = "http://localhost:3100/friends/requests";
class Invitations extends React.Component{
    constructor(props) {
        super(props);
        this.state= {
            sendInvitations: [],
            receivedInvitations:[],
            counter: 0
        }
    }
    visible
    componentDidMount() {
        InvitationService.getSendRequests().then(
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
                console.log(this.state)
        });

        InvitationService.getReceivedRequests().then(
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
        axios.delete(API_URL+ `/${id}`)
            .then(res => {
                console.log(res);
                const sendInvitations = this.state.sendInvitations.filter(item => item.requestFriendId !== id);
                this.setState({ sendInvitations });
            })
    }

    updateInvitationStatus(id, status, e){
        axios.patch(API_URL+ `/${id}` +"?requestStatus="+`${status}`)
            .then(res =>{
                InvitationService.getReceivedRequests().then(
                    (response) => {
                        this.setState({ receivedInvitations:response.data });
                    });
/*
                console.log(res);
                console.log(this.state.receivedInvitations);
                const receivedInvitations = this.state.receivedInvitations.filter(item => item.requestFriendId !== id);
                console.log(receivedInvitations);
                this.setState({ receivedInvitations });*/
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
                        <td> Invitation id</td>
                        <td> Invitation state</td>
                        <td> Invitation from</td>
                        <td> Invitation to</td>
                        <td> Invitation message</td>

                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.receivedInvitations.map(
                            invitation => {
                                const visibility = invitation.requestStatus === "WAITING" ? classes.visible : classes.hidden
                                return <tr>
                                    <td>{invitation.requestFriendId}</td>
                                    <td>{invitation.requestStatus}</td>
                                    <td>{invitation.fromUserId.firstName+ " "+ invitation.fromUserId.lastName}</td>
                                    <td>{invitation.toUserId.firstName+ " "+ invitation.toUserId.lastName}</td>
                                    <td>{invitation.message}</td>
                                    <td><button className={"btn btn-success " + visibility} onClick={(e) => this.updateInvitationStatus(invitation.requestFriendId,"ACCEPTED", e)}>Accept</button></td>
                                    <td><button className={"btn btn-danger " + visibility} onClick={(e) => this.updateInvitationStatus(invitation.requestFriendId,"REJECTED", e)}>Reject</button></td>
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
                            <td> Invitation id</td>
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
                                    return <tr>
                                        <td>{invitation.requestFriendId}</td>
                                        <td>{invitation.requestStatus}</td>
                                        <td>{invitation.fromUserId.firstName+ " " + invitation.fromUserId.lastName}</td>
                                        <td>{invitation.toUserId.firstName+ " "+ invitation.fromUserId.lastName}</td>
                                        <td>{invitation.message}</td>
                                        <td><button className="btn btn-danger" onClick={(e) => this.cancelInvitation(invitation.requestFriendId, e)}>Cancel</button></td>
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