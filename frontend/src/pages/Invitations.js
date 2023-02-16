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
/*    deleteInvitation(id){
    InvitationService.deleteRequest(id).then(response =>{
    this.setState({sendInvitations:this.state.sendInvitations
                      .filter(sendInvitation =>sendInvitation.requestFriendId !== id)})
});
}*/


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
                                    <td>{invitation.fromUserId.firstName+ " "+ invitation.fromUserId.lastName}</td>
                                    <td>{invitation.toUserId.firstName+ " "+ invitation.toUserId.lastName}</td>
                                    <td>{invitation.message}</td>
                                    <button className="btn btn-danger" onClick={InvitationService.updateRequest(invitation.requestFriendId,"ACCEPTED")}>Accept</button>
                                    <button className="btn btn-danger" onClick={InvitationService.updateRequest(invitation.requestFriendId,"REJECTED")}>Reject</button>
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
                                        <button className="btn btn-danger" onClick={ InvitationService.deleteRequest(invitation.requestFriendId)}>Cancel Invitation</button>
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