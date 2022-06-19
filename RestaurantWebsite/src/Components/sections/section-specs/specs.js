import React from 'react';
import {Container,Row,Col} from 'reactstrap';
import Spec from './spec/spec';
import clock from '../../../Assests/icons/icon_specs/clock.png';
import chef from '../../../Assests/icons/icon_specs/hat.png';
import leaf from '../../../Assests/icons/icon_specs/leaf.png';
import basket from '../../../Assests/icons/icon_specs/basket.png';

const specs=()=>{
    return(
        <Container>
            <Row>
                <Col>
                    <Spec src={basket} head="100% organic" content="Only fresh ingredients used in all dishes, sourced directly from local farms." />
                </Col>
                <Col>
                    <Spec src={clock} head="Fast Delivery" content="Our delivery drivers have plenty of experience outrunning even the police. Your order will arrive faster than an ambulance."/>
                </Col>
                <Col>
                 <Spec src={leaf} head="Efficiency" content="Our chefs use quantum computing and time crystals to begin preparing your order before you even request it."/>
                </Col>
                <Col>
                 <Spec src={chef} head="Experienced Chefs" content="Chefs are trained to physically withstand temperatures of up to 3000°F, allowing them to be present in the oven along with your order to ensure perfection."/>
                </Col>
            </Row>
        </Container>
    );
}

export default specs;