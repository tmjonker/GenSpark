import React from 'react';
import './inOption.css';

import {NavLink} from 'react-router-dom';


const inOption = (props) => {
    let value=(
        
        <div className="inOption">
        <p className="inHead">{props.head}</p>
        <div className="inImgBox">
            <img src={props.image} alt="Maccain"/>
        </div>
       
        <p className="inContent">{props.content}</p>
        <p className="inPrice">$ {props.price} </p>
       
       
        <button type="button" className="inOpButton" onClick={()=>props.add({id: props.id,head:props.head,counter:1,url:props.image,price:props.price,content:props.content})} >Add to Cart</button>
        <NavLink to="/order" onClick={()=>props.add({id: props.id,head:props.head,counter:1,url:props.image,price:props.price,content:props.content})}>Order Now</NavLink>
    </div>
    );
    let result =props.check!==null?value:null;
    return (
        <div>
      {result}
      </div>
    );
}

export default inOption;