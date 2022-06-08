import React from "react";

function Paragraph(props) {

    return (

        <p class={props.class}>
            {props.text}
        </p>
    );
}

export default Paragraph;