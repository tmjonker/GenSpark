import React,{Component} from 'react';
import './accountInfo.css';
import {Container,Row,Col} from 'reactstrap';

class  orderForm extends Component{
    state={
        username:JSON.parse(localStorage.getItem("user")).username,
    }
    render(){
        return(
        <div className="OrderForm">
        <p className="OrderFormHead">Account Information</p>
            <form id="orderForm">
                <div className="form__container">
                <Container>
                    <Row>
                        <Col xs="4" xl="4">
                            <legend>
                                <p>Username: </p>
                            </legend>
                        </Col>
                        <Col xs="8" xl="8">
                            <legend>
                                <p>{this.state.username}</p>
                            </legend>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="4" xl="4">
                            <legend>
                                <p>Total Purchase Amount: </p>
                            </legend>
                        </Col>
                        <Col xs="8" xl="8">
                            <legend>
                                <p>Unfinished</p>
                            </legend>
                        </Col>
                    </Row>
                </Container>
                </div>
            </form>
        </div>
    );
}
}

export default orderForm;