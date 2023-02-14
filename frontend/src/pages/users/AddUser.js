import {useState} from "react";

export default function AddUser() {
    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        city: "",
    });

    const {firstName, lastName, email, password, city} = user;

    const onInputChange = (e) => {
        setUser({...user, [e.target.firstName]: e.target.value})
    }

    const onSubmit = (e) => {
        e.preventDefault();
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-venter m-4"> Join us! </h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="First name" className="form-label">
                                First name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your first name"
                                name="name"
                                value={firstName}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Last name" className="form-label">
                                Last name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your last name"
                                lastName="lastName"
                                value={lastName}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                Email address
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your email address"
                                email="email"
                                value={email}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Password" className="form-label">
                                Password
                            </label>
                            <input
                                type = {"text"}
                                className="form-control"
                                placeholder="Enter your password"
                                password = "password"
                                value={password}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="City" className="form-label">
                               City
                            </label>
                            <input
                                type = {"text"}
                                className="form-control"
                                placeholder="Enter your city"
                                city = "city"
                                value={city}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type = "submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                    </form>

                </div>
            </div>
        </div>
)
}