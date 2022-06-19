import React, { Component } from 'react';
import './OrderOnline.css';

import axios from 'axios';
import Toolbar from '../../Components/navigation/toolbar/toolbar';
import { NavLink } from 'react-router-dom';
import Items from '../../Components/Order/Items/Items';
import Form from '../../Components/Order/orderForm/orderForm';
import Footer from '../../Components/navigation/footer/footer';
class OrderOnline extends Component {

    placeOrder = (obj) => {

            let date = new Date();
            let month = date.getMonth() + 1;
            let day = date.getDate();
            let year = date.getFullYear();

            let filteredCart = this.props.data.filter(element => element.counter > 0);

            let total = 0;
            filteredCart.map(item => total += item.price * item.counter);
            let menuItems = [];
            filteredCart.map(item => menuItems.push(item.id));

            console.log(obj);

            let address = {
                name : obj.checkOut.name,
                address1 : obj.checkOut.address1,
                address2 : obj.checkOut.address2,
                city : obj.checkOut.city,
                state : obj.checkOut.state,
                zipCode : obj.checkOut.zipCode
            }

            let copy = {
                date : month + "/" + day + "/" + year,
                price : total,
                username : JSON.parse(localStorage.getItem("user")).username,
                menuIds : menuItems,
                address : address
            }

            
            if (filteredCart.length > 0) {
                axios.post("http://localhost:8080/purchase", copy).then(()=>alert("Your Order is Placed!"));
            }
            else {
                alert("Please select some items from Menu first");
            }

            window.location.reload();
        }


        render() {
            return (
                <div className="OrderOnline">
                    <section className="Order">
                        <Toolbar count={this.props.count} />
                        <p className="OrderHead">Order Online</p>
                        <div>
                            <NavLink to="/">Home</NavLink>
                            <NavLink to="/menu">Menu</NavLink>
                            <NavLink to="/order">Order Online</NavLink>
                        </div>
                    </section>
                    <section className="Orderitems">
                        <Items data={this.props.data} />
                    </section>
                    <section className="order-sec">
                        <Form place={this.placeOrder} />
                    </section>
                    <Footer />
                </div>
            );
        }
    }
    export default OrderOnline;