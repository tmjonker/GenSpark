import React, { Component } from 'react';
import './Offer.css';
import Toolbar from '../../Components/navigation/toolbar/toolbar';
import { Container, Row, Col } from 'reactstrap';
import Tile from '../../Components/sections/section-tiles/tile/tile';
import Footer from '../../Components/navigation/footer/footer';
import axios from 'axios';
class Offer extends Component {

    state = {
        categories: [],
        options: [],
        load: "Select From Above",
        rewardData: [],
        showCustomize: false,
        sendData: {}
    }
    componentDidMount() {
        let user = {
            username: null,
        }

        try {
            user.username = JSON.parse(localStorage.getItem("user")).username;
        } catch (error) {
            
        }

        axios.post("http://localhost:8080/rewards", user).then((response) => this.setState({ rewardData: response.data }));
        console.log("reward data" + this.rewardData);
    }

    render() {

        if (localStorage.getItem("user") === null) {
            window.location.href='/';
        } else {

            return (
                <div className="Offer">
                    <section className="Offers">
                        <Toolbar count={this.props.count} />
                        <p className="OffersHead">Rewards</p>
                        <div>
                            {/* <NavLink to="/">Home</NavLink>
                        <NavLink exact to="/menu">Menu</NavLink>
                        <NavLink to="/offers">Offers</NavLink> */}

                            {/* you would have to have the spring boot application running concurrently on your computer... 
                        then the http address for the call would be http://localhost:8080/api/menu  or 
                        http://localhost:8080/rewards later on once that functionality gets added */}
                        </div>
                    </section>
                    <section style={{ marginTop: "40px" }}>
                        <Container>
                            <Row>
                                {this.state.rewardData.map(each => <Col key={each.id}><Tile url="/menu" button="Visit Menu" head={each.rewardName} content={each.rewardDescription} /></Col>)}
                            </Row>
                        </Container>
                    </section>
                    <div className="spacer">

                    </div>
                    <Footer count={this.props.count} />
                </div>
            );
        }
    }
}

export default Offer;