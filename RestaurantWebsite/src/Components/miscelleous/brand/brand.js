import React from 'react';
import './brand.css';
import logo from '../../../Assests/icons/white-hand.png';

const brand=(props)=>{
    let vclass=" ";
    if(props.show){
        vclass="show";
    }
    let all_class=["brand"];
    all_class.push(vclass);
    return(
        <div className={all_class.join(" ")}>
            <p className="brandnum">M.</p>
            <p className="name">Brothers</p>
            <div className="image-container">
            <img src={logo} alt="Compoany Logo"/>
            </div>
            <p className="lastTitle">Italian Cuisine</p>
        </div>

    );
}

export default brand;