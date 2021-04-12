/*!

=========================================================
* Black Dashboard React v1.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";

// reactstrap components
import {
    Card,
    CardHeader,
    CardBody,
    CardTitle,
    Table,
    Row,
    Col, Button,
} from "reactstrap";

class Tables extends React.Component {
  constructor(props) {
    super(props);

    this.timer = null


    this.state = {
      table_data : [],
      table_buttons:[],
      curent_page:1,
      num_to_show:15,

    }
  }

  getData = async (id) => {
      try {
           const response = await fetch(
              `/api/coins`
          );

          const result = await response.json();
          this.setState(
              prevState => (
                  {
                      table_data: result
                  }
              )
          );
          console.log(result)

      }
    catch(e){
         this.setState(
             prevState => (
                 {
                     error: "No accidents do Show"
                 }
             )
         );
     }
  }
  renderArray = (value,index) => {
    return(
      <tr>
        <td className="text-center">{value["symbol"]}</td>
        <td className="text-center">{value["priceChange"]}$</td>
        <td className="text-center">{value["priceChangePercent"]}%</td>
        <td className="text-center">{value["lastPrice"]}$</td>
      </tr>

    )
  }

  renderButtons(){

     if(this.state.table_data.length > this.state.num_to_show){
      const Buttons = []
      if(this.state.curent_page >1)
        Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,this.state.curent_page-1)}><i
                       className="fas fa-angle-left"></i></a></li>)
      for (let i = 1; i < Math.ceil(this.state.table_data.length/this.state.num_to_show)+1; i++) {
        if(i==this.state.curent_page){
          Buttons.push(<li className="page-item active"><a className="page-link" onClick={(e)=>this.handleClick(e,`${i}`)}>{i}</a></li>)
        }
        else{
          Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,`${i}`)}>{i}</a></li>)
        }
      }
      if(this.state.curent_page < Math.ceil(this.state.table_data.length/this.state.num_to_show) )
        Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,parseInt(this.state.curent_page)+1)}><i
                       className="fas fa-angle-right"></i></a></li>)

      return(
              <div className="row justify-content-center">
                 <nav aria-label="Page navigation example">
                     <ul className="pagination">
                        {Buttons}
                     </ul>
                 </nav>
              </div>
      )}
      else{
          return
      }
  }

    componentDidMount() {
    this.getData(this.state.curent_page);
    this.timer = setInterval(() => this.getData(this.state.curent_page), 3000)
  }

  componentWillUnmount(){
    clearInterval(this.timer)
    this.timer = null
  }

  handleClick = (e,id) => {
    e.preventDefault();
    this.state.curent_page=id
    this.getData(id);
  };


  render(){

    return (
        <>
          <div className="content">
            <Row>
              <Col md="12">
                <Card>
                  <CardHeader>
                    <CardTitle tag="h4">Coins Table</CardTitle>
                      {this.renderButtons()}
                  </CardHeader>
                  <CardBody>
                    <Table className="tablesorter" >
                      <thead className="text-primary">
                      <tr>
                        <th className="text-center">Names</th>
                        <th className="text-center">Price Change(24h)</th>
                        <th className="text-center">Variation(24h)</th>
                        <th className="text-center">price</th>
                      </tr>
                      </thead>
                        <tbody>
                        {this.state["table_data"].slice((this.state.curent_page-1)*this.state.num_to_show,
                            this.state.curent_page*this.state.num_to_show+this.state.num_to_show).map(this.renderArray)}
                        </tbody>
                    </Table>
                  </CardBody>
                </Card>
              </Col>
              {/*<Col md="12">*/}
              {/*  <Card className="card-plain">*/}
              {/*    <CardHeader>*/}
              {/*      <CardTitle tag="h4">Table on Plain Background</CardTitle>*/}
              {/*      <p className="category">Here is a subtitle for this table</p>*/}
              {/*    </CardHeader>*/}
              {/*    <CardBody>*/}
              {/*      <Table className="tablesorter" responsive>*/}
              {/*        <thead className="text-primary">*/}
              {/*          <tr>*/}
              {/*            <th>Name</th>*/}
              {/*            <th>Country</th>*/}
              {/*            <th>City</th>*/}
              {/*            <th className="text-center">Salary</th>*/}
              {/*          </tr>*/}
              {/*        </thead>*/}
              {/*        <tbody>*/}
              {/*          <tr>*/}
              {/*            <td>Dakota Rice</td>*/}
              {/*            <td>Niger</td>*/}
              {/*            <td>Oud-Turnhout</td>*/}
              {/*            <td className="text-center">$36,738</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Minerva Hooper</td>*/}
              {/*            <td>Curaçao</td>*/}
              {/*            <td>Sinaai-Waas</td>*/}
              {/*            <td className="text-center">$23,789</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Sage Rodriguez</td>*/}
              {/*            <td>Netherlands</td>*/}
              {/*            <td>Baileux</td>*/}
              {/*            <td className="text-center">$56,142</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Philip Chaney</td>*/}
              {/*            <td>Korea, South</td>*/}
              {/*            <td>Overland Park</td>*/}
              {/*            <td className="text-center">$38,735</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Doris Greene</td>*/}
              {/*            <td>Malawi</td>*/}
              {/*            <td>Feldkirchen in Kärnten</td>*/}
              {/*            <td className="text-center">$63,542</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Mason Porter</td>*/}
              {/*            <td>Chile</td>*/}
              {/*            <td>Gloucester</td>*/}
              {/*            <td className="text-center">$78,615</td>*/}
              {/*          </tr>*/}
              {/*          <tr>*/}
              {/*            <td>Jon Porter</td>*/}
              {/*            <td>Portugal</td>*/}
              {/*            <td>Gloucester</td>*/}
              {/*            <td className="text-center">$98,615</td>*/}
              {/*          </tr>*/}
              {/*        </tbody>*/}
              {/*      </Table>*/}
              {/*    </CardBody>*/}
              {/*  </Card>*/}
              {/*</Col>*/}
            </Row>
          </div>
        </>
    );
  }
  }
export default Tables;