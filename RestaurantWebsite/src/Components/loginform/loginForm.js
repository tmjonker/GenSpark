import React,{Component} from 'react';
import './loginForm.css';
import {Container,Row,Col} from 'reactstrap';

class  orderForm extends Component{
    state={
        password:"",
        username:"",
    }
    passHandler=(e)=>{
        this.setState({password:e.target.value});
    }
    nameHandler=(e)=>{
        this.setState({username:e.target.value});
    }
    render(){
        return(
        <div className="OrderForm">
        <p className="OrderFormHead">Login</p>
            <form id="orderForm">
                <div className="form__container">
                <Container>
                    <Row>
                        <Col xs="4" xl="4">
                            <legend>
                                <p>Username</p>
                            </legend>
                        </Col>
                        <Col xs="8" xl="8">
                        <input type="text" value={this.state.username} onChange={this.nameHandler} id="Email"/>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="4" xl="4">
                            <legend>
                                <p>Password</p>
                            </legend>
                        </Col>
                        <Col xs="8" xl="8">
                        <input type="password" value={this.state.password} onChange={this.passHandler} id="Pass"/>
                        </Col>
                    </Row>
                </Container>
                </div>
                <button type="button" onClick={()=>this.props.place(this.state.username, this.state.password)}>Login</button>
            </form>
        </div>
    );
}
}

export default orderForm;