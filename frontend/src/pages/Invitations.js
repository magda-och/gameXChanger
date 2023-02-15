import React from "react";
import InvitationService from "../services/InvitationService";

class Invitations extends React.Component{
    constructor(props) {
        super(props);
        this.state= {
            sendInvitations: [],
            receivedInvitations:[]
        }
    }

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

    render() {
        return(
            <div>
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
                                return <tr>
                                    <td>{invitation.requestFriendId}</td>
                                    <td>{invitation.requestStatus}</td>
                                    <td>{invitation.fromUserId.toString()}</td>
                                    <td>{invitation.toUserId.toString()}</td>
                                    <td>{invitation.message}</td>
                                    <button>Accept</button>
                                    <button>Reject</button>

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
                                        <td>{invitation.fromUserId.toString()}</td>
                                        <td>{invitation.toUserId.toString()}</td>
                                        <td>{invitation.message}</td>
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