import React, { Component } from 'react';
import './Login.css';
import axios from 'axios';
import Toolbar from '../../Components/navigation/toolbar/toolbar';
import Form from '../../Components/loginform/loginForm';
import Register from '../../Components/registerform/registerForm';
import Footer from '../../Components/navigation/footer/footer';
class Login extends Component {

    logIn = (name, pass) => {
        var user = {
            username:name,
            password:pass
        };
        axios.post("http://localhost:8080/authenticate", user).then(response => {
            localStorage.setItem("user", JSON.stringify(response.data));
            localStorage.setItem("signedIn", "true");
            alert("Login Successful, JWT Token: " + JSON.parse(localStorage.getItem("user")).token);
            window.location.href='/';
        }).catch(err=>alert("Invalid Login Information"));

        
    }
    
    reg = (name, pass, mail) => {
        var user = {
            username:name,
            password: pass,
            email: mail,
            date: new Date().toString()
        };

        axios.post("http://localhost:8080/register", user).then(response => {
            alert("Account Created");
        });

    }


        render() {
            return (
                <div className="OrderOnline">
                    <section className="Order">
                        <Toolbar count={this.props.count} />
                        <p className="OrderHead">Login / Sign Up</p>
                    </section>
                    <section className="order-sec">
                        <Form place={this.logIn} />
                    </section>
                    <section className="order-sec">
                        <Register place={this.reg} />
                    </section>
                    <Footer />
                </div>
            );
        }
    }
    export default Login;