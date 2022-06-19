import React, { Component } from 'react';
import './orderForm.css';
import { Container, Row, Col } from 'reactstrap';
import axios from 'axios';

class orderForm extends Component {
    state = {
        name: "",
        zipCode: "",
        address1: "",
        city: "",
        state: "",
        address2: "",
        addresses: []
    }
    nameHandler = (e) => {
        this.setState({ name: e.target.value });
    }
    phoneHandler = (e) => {
        this.setState({ zipCode: e.target.value });
    }
    houseHandler = (e) => {
        this.setState({ address1: e.target.value });
    }
    localityHandler = (e) => {
        this.setState({ address2: e.target.value });
    }
    pincodeHandler = (e) => {
        this.setState({ state: e.target.value });
    }
    landHandler = (e) => {
        this.setState({ city: e.target.value });
    }

    componentDidMount() {

        let username = JSON.parse(localStorage.getItem("user")).username;

        axios.get("http://localhost:8080/user/" + username).then((response) => {
            this.setState({ addresses: response.data, loaded: true });
        }).catch(err => console.log("Couldn't load menu data"));
    }

    selectOnChange = () => {
        if (document.getElementById("addresses").value !== "none") {

            let address = JSON.stringify(this.state.addresses.filter(add => add.address1 === document.getElementById("addresses").value));
            address = JSON.parse(address);

            this.setState({
                name : address[0].name,
                address1 : address[0].address1,
                address2 : address[0].address2,
                city : address[0].city,
                state : address[0].state,
                zipCode : address[0].zipCode
            });
        }

    }

    buttonOnSubmit = () => {

        var checkOut = {
            name: this.state.name,
            zipCode: this.state.zipCode,
            address1: this.state.address1,
            address2: this.state.address2,
            city: this.state.city,
            state: this.state.state
        };

        this.setState({
            name : "",
            address1 : "",
            address2 : "",
            city : "",
            state : "",
            zipCode : ""
        });

        this.props.place({ checkOut });
    }

    render() {


        return (
            <div className="OrderForm">
                <p className="OrderFormHead">Fill Delivery Details</p>
                <form id="orderForm">
                    <div className="form__container">
                        <Container>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>Saved Addresses</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <select onChange={this.selectOnChange} id="addresses">
                                        <option value="none">None</option>
                                        {this.state.addresses.map(add => <option value={add.address1}>{add.address1}</option>)}
                                    </select>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>Name</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.name} onChange={this.nameHandler} required id="name" />
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>Address</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.address1} onChange={this.houseHandler} required id="address1" />
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>Address 2</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.address2} onChange={this.localityHandler} required id="address2" />
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>City</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.city} onChange={this.landHandler} required id="city" />
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>State</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.state} onChange={this.pincodeHandler} required id="state" />
                                </Col>
                            </Row>
                            <Row>
                                <Col xs="4" xl="4">
                                    <legend>
                                        <p>Zip Code</p>
                                    </legend>
                                </Col>
                                <Col xs="8" xl="8">
                                    <input type="text" value={this.state.zipCode} onChange={this.phoneHandler} required id="zipCode" />
                                </Col>
                            </Row>
                        </Container>
                    </div>
                    <button type="button" onClick={() => this.buttonOnSubmit()}>Place Order</button>
                </form>

                <p className="OrderFormNotice">*Payment will be takes as Cash On delivery</p>
            </div>
        );
    }
}

export default orderForm;