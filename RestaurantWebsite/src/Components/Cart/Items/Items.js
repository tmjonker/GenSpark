import React from 'react';
import Item from './Item/Item';
import {Container} from 'reactstrap';
const Items=(props)=>{

    let filteredCart = props.data.filter(element => element.counter > 0);

    return(
        <div className="Items">
            <Container>

            {filteredCart.map(element=>{
                return <Item key={element.id} decrement={props.remove} increment={props.adding} head={element.head} cnt={element.counter} price={element.price} url={element.url} content={element.content} />
        })}
            </Container>
            
        </div>
    );
}

export default Items;