import React from "react";

function Picture(props) {

    return (

        <img class={props.class} src={props.source} alt={props.desc} />
    );
}

export default Picture;