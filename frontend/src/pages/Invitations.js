import React from "react";
import InvitationService from "../services/InvitationService";

class Invitations extends React.Component{
    constructor(props) {
        super(props);
        this.state= {
            invitations: []
        }
    }

    componentDidMount() {
        InvitationService.getAllRequests().then(
            (response) => {
                //this.state.invitations = response.data
                this.setState({ invitations: [{
                        id: 1,
                        requestStatus: 1,
                        fromUserId: 1,
                        requestFriendId: 1
                    }] });
                this.setState({ invitations:response.data });
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
                        this.state.invitations.map(
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
                            this.state.invitations.map(
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