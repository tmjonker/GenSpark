import React from 'react';
import './flip-card.css';

import {Link} from 'react-router-dom';
const flip_card = (props) => {
    return (
        <div className="flip-card">
            <div className="flip-card-inner">
                <div className="flip-card-front">
                {props.front}
                    <div>
                    <h1>The best food you'll never eat</h1>
                    <div style={{marginTop:"13%",backgroundColor:"#242424",opacity:0.8,display:"inline-block",padding:"10px 10px"}}>
                        <h2>Taste the rainbow</h2>
                        
                        <h3>Brought to you by the M. Brothers</h3>
                        <h4>Click To take a look</h4>
                        </div>
                    </div>
                     </div>
                    <div className="flip-card-back">
                    {props.back}
                        <h1 style={{fontWeight:"300"}}>Whats Top on the board</h1>
                        <ul>
                            {Object.keys(props.lis).map(each=><li key={each}>{each}</li>)}
                        </ul>
                        <Link to="/home" >Happy Ordering</Link>            
                    </div>
                </div>
            </div>
            );
}
export default flip_card;