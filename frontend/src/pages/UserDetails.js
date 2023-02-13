import {useParams} from "react-router-dom";

function UserDetailsPage() {
    const params = useParams();
    return (
        <>
            <h1>User Details!</h1>
            <p>{params.userId}</p>
        </>
    );
}

export default UserDetailsPage;