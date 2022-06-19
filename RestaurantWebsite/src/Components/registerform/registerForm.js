import React,{Component} from 'react';
import './registerForm.css';
import {Container,Row,Col} from 'reactstrap';

class  orderForm extends Component{
    state={
        username:"",
        password:"",
        email:"",
        date:"",
    }
    nameHandler=(e)=>{
        this.setState({username:e.target.value});
    }
    passHandler=(e)=>{
        this.setState({password:e.target.value});
    }
    emailHandler=(e)=>{
        this.setState({email:e.target.value});
    }
    dateHandler=(e)=>{
        this.setState({date:e.target.value});
    }
    render(){
        return(
        <div className="OrderForm">
        <p className="OrderFormHead">Register</p>
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
                        <input type="text" value={this.state.username} onChange={this.nameHandler} id="Name"/>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="4" xl="4">
                            <legend>
                                <p>E-mail</p>
                            </legend>
                        </Col>
                        <Col xs="8" xl="8">
                        <input type="text" value={this.state.email} onChange={this.emailHandler} id="Email"/>
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
                <button type="button" onClick={()=>this.props.place(this.state.username, this.state.password, this.state.email)}>Create Account</button>
            </form>
        </div>
    );
}
}

export default orderForm;