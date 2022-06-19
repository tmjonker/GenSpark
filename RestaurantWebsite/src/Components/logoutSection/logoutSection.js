import React,{Component} from 'react';
import './logoutSection.css';
class  orderForm extends Component{
    render(){
        return(
        <div className="OrderForm">
            <form id="orderForm">
                <button type="button" onClick={()=>this.props.place()}>Log Out</button>
            </form>
        </div>
    );
}
}

export default orderForm;