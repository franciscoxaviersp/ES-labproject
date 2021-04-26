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
    Col, Button, DropdownToggle, DropdownMenu, DropdownItem, ButtonDropdown,
} from "reactstrap";


class Logs extends React.Component {
  constructor(props) {
    super(props);

    this.timer = null
    this.toggleDrop1 = this.toggleDrop1.bind(this);
    this.changeValueDrop1 = this.changeValueDrop1.bind(this);

    this.state = {
      table_data : [],
      table_buttons:[],
      curent_page:1,
      num_to_show:10,
      dropDown1Value: "Name",
      dropdownIndex:"between",
      dropdownIndex2:"Today",
      dropdownIndex3:"Descending",
      dropDown1Open: false,

    }
}

  sortData(result){
      console.log(this.state.table_data)
          console.log(this.state.dropDown1Value)
      if (this.state.dropDown1Value == "Name")
              result.sort(function(a, b) {
                  if (a.symbol< b.symbol)
                    return -1;
                  if (a.symbol > b.symbol)
                    return 1;
                  // names must be equal
                  return 0;
                });
      if (this.state.dropDown1Value == "Price"){
              result.sort(function(a, b) {return a.lastPrice - b.lastPrice });
      }
      if (this.state.dropDown1Value == "Variation"){
              result.sort(function(a, b) {return a.priceChangePercent - b.priceChangePercent });
      }

  }




  getData = async (id) => {
      try {
           let response = await fetch(
              `/test/logs`
          );
           let result = await response.json();
          console.log(result.length)

          this.setState(
              prevState => (
                  {
                      table_data: result.slice(result.length-100,result.length).reverse()
                  }
              )
          );


      }
    catch(e){
         console.log(e)
     }
  }

  /* DropDown functions */
  toggleDrop1() {
    this.setState({dropDown1Open: !this.state.dropDown1Open});
  }
   changeValueDrop1(e,id) {
      const a = ["","between","cars","people","injured","severity","status"]
      this.state.dropDown1Value= e.currentTarget.textContent
      this.state.dropdownIndex= `${a[id]}`
      this.getData(this.state.curent_page)
  }

  renderArray = (value,index) => {
    return(
      <tr>
        {/*<td className="text-center">/!*<img src={"https://cryptoicons.org/api/icon/"+ value["symbol"].split("EUR")[0].toLowerCase()+"/200"} alt="icon"/>*!/ {value["symbol"]}</td>*/}
          <td ><b>{value.split(" ")[0]} {value.split(" ")[1].split(",")[0]}</b> {value.split(",")[1].split(/ (.+)/)[1]}</td>
        {/*<td className="text-center">{value["lastPrice"]}€</td>*/}
        {/*  <td className="text-center">{value["priceChange"]}€</td>*/}
        {/*<td className="text-center">{value["priceChangePercent"]}%</td>*/}
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
    this.timer = setInterval(() => this.getData(this.state.curent_page), 2000)
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
                    <Row >
                      <Col><CardTitle tag="h3">Logs</CardTitle></Col>
                      <Col>
                      {this.renderButtons()}
                      </Col>
                    <Col>
                    {/*   <div className="row justify-content-end" style={{ paddingRight: 20}}>*/}
                    {/*       <h4 className="mr-2 mt-2 text-white text-center">Sort by: </h4>*/}
                    {/*  <ButtonDropdown isOpen={this.state.dropDown1Open} toggle={this.toggleDrop1}>*/}
                    {/*  <DropdownToggle caret>*/}
                    {/*    {this.state.dropDown1Value}*/}
                    {/*  </DropdownToggle>*/}
                    {/*  <DropdownMenu right>*/}
                    {/*    <DropdownItem onClick={(e)=>this.changeValueDrop1(e,1)}>Name</DropdownItem>*/}
                    {/*    <DropdownItem onClick={(e)=>this.changeValueDrop1(e,2)}>Price</DropdownItem>*/}
                    {/*    <DropdownItem onClick={(e)=>this.changeValueDrop1(e,3)}>Variation</DropdownItem>*/}
                    {/*  </DropdownMenu>*/}
                    {/*</ButtonDropdown>*/}
                    {/*       </div>*/}
                  </Col>
                 </Row>
                  </CardHeader>
                  <CardBody>
                    <Table className="tablesorter" >
                      <thead className="text-primary">
                      <tr>
                        <th >Description</th>
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
            </Row>
          </div>
        </>
    );
  }
  }
export default Logs;