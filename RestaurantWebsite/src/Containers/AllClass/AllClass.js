import React,{Component} from 'react';
import Home from '../Home/Home';
import Menu from '../Menu/Menu';
import Order from '../OrderOnline/OrderOnline';
import Cart from '../Cart/Cart';
import {Switch,Route} from 'react-router-dom';
import Offer from '../Offer/Offer';
import axios from 'axios';
import ForLoad from '../../Components/miscelleous/forLoad';
import Login from '../Login/Login';
import Account from '../Account/Account';

class allClass extends Component{
    state={
        item:[],
        menudata:[],
        loaded:false
      };
      componentDidMount(){
        axios.get("http://localhost:8080/api/menu").then((response)=>{
            this.setState({menudata:response.data,loaded:true});
        }).catch(err=>console.log("Couldn't load menu data"));
      }
      addItem=(obj)=>{
          let extra=[...this.state.item];
          var check=false;
          extra.forEach(element=>{
              if(element.head===obj.head){
                check=true;  
                element.counter+=1;
              }

          });
          if(!check){
            extra.push(obj);
          }

          this.setState({item:extra});
          
          alert(`${obj.head} is added to your cart`);
      }
      removeItem=(obj)=>{
        var copy=[...this.state.item];
        var check=false;
        //let pos=-1;
        let pos=0;
        copy.forEach(element=>{
            
            if(element.head===obj.head && element.counter>=1 ){
                element.counter=element.counter-1;
                check=true;
            }
            if(check){
                check=false;
                if(element.counter===0){
                    copy.splice(pos, 1)
                    //copy=copy.slice(0,pos).concat(copy.slice(pos+1));
                    
                }
            }
            pos+=1;
        });
        this.setState({item:copy});
        
      }

      clearCart = () => {

        this.setState({item:[]});
      }
      
    render(){
        
        const ddt=this.state.loaded?(
            <div>
            <Switch>
                <Route path="/rewards" component={()=><Offer count={this.state.item.length}/>}/>
   <Route path="/cart" component={()=><Cart adding={()=>this.addItem} remove={()=>this.removeItem} data={this.state.item}/> }/>
   <Route path="/menu" component={()=><Menu inbox={this.state.item.length} menudata={this.state.menudata} loaded={this.state.loaded} adding={()=>this.addItem}/>}/>
   <Route path="/order" component={()=><Order remove={this.clearCart} count={this.state.item.length} data={this.state.item}/>}/>
   <Route path="/login" component={()=><Login count={this.state.item.length}/>}/>
   <Route path="/account" component={()=><Account count={this.state.item.length}/>}/>
   <Route path="/" component={()=><Home count={this.state.item.length}/>}/>
   
   </Switch>
       </div>
        ):<ForLoad/>;
        return(
           ddt
        );
    }
}

export default allClass;

