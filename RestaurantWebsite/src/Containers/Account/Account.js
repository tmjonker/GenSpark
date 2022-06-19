import React, { Component } from 'react';
import './Account.css';
import Toolbar from '../../Components/navigation/toolbar/toolbar';
import AccountInfo from '../../Components/accountInfo/accountInfo';
import Logout from '../../Components/logoutSection/logoutSection';
import Footer from '../../Components/navigation/footer/footer';
class Login extends Component {

    state={
        username:JSON.parse(localStorage.getItem("user")).username,
    }

    logOut = () => {
        localStorage.removeItem("user");
        localStorage.removeItem("signedIn");
        //useNavigate("/");
        window.location.href='/';
    }

        render() {
            return (
                <div className="OrderOnline">
                    <section className="Order">
                        <Toolbar count={this.props.count} />
                        <p className="OrderHead">Hello {this.state.username}</p>
                    </section>
                    <section className="order-sec">
                        <AccountInfo/>
                    </section>
                    <section className="order-sec">
                        <Logout place={this.logOut} />
                    </section>
                    <Footer />
                </div>
            );
        }
    }
    export default Login;