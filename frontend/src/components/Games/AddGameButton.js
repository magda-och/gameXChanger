import React from "react";

function AddGameButton({title}) {

    const [showForm, setShowForm] = React.useState(false);
    const openForm = () => setShowForm(true);

    return (
        <div>
            <button onClick={openForm}> {title} </button>


        </div>
    );
}

export default AddGameButton;

