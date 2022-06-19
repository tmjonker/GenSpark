import React from 'react';
import './specOffer.css';
import Button from '../../buttons/grab-offer/grab-btn';

const specOffer=(props)=>{
    return(
        <div className="specOffer">
        <div className="offerArrange">
            <p className="offerHead">AUTHENTIC ITALIAN DISHES</p>
            <p className="offerContent">Free Drinks From Tap With purchase</p>
            <p className="offerStarting">starting at </p>
             <p className="offerDigit">$1.99</p>
             <div className="offerButton">
                 <Button url="/menu" content="See Menu"/>
             </div>
            </div>
        </div>
    );
}

export default specOffer;