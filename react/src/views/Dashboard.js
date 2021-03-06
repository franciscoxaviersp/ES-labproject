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
//import Chart from "react-google-charts";
import Chart from "react-apexcharts";
// nodejs library that concatenates classes
import classNames from "classnames";
// react plugin used to create charts
import { Line, Bar } from "react-chartjs-2";
// reactstrap components
import {
  Button,
  ButtonGroup,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  Label,
  FormGroup,
  Input,
  Table,
  Row,
  Col,
  UncontrolledTooltip,
} from "reactstrap";
import { Alert } from 'reactstrap';
// core components
import {
  chartExample1,
  chartExample2,
  chartExample3,
  chartExample4,
} from "variables/charts.js";
import Notify from 'react-notification-alert';


class Dashboard extends React.Component {

  constructor(props) {
    super(props);
    this.onDismiss = this.onDismiss.bind(this);


    this.state = {
        visible: true,
        msg:"ola",
        changes:[],
      series: [{
              data: [{
                x: Date.parse("0"),
                  y: [0,0,0,0]
                }
              ]
            }],
      options: {
              chart: {
                type: 'candlestick',


              },
              title: {
                text: 'CandleStick Chart',
                align: 'left',
                  style: {
                      fontSize:  '14px',
                      fontWeight:  'bold',
                      fontFamily:  undefined,
                      color:  '#FFF'
                    },

              },
              xaxis: {
                type: 'datetime',
                labels: {
                  style: {
                      colors: '#FFF',
                      fontSize: '12px',
                      fontFamily: 'Helvetica, Arial, sans-serif',
                      fontWeight: 400,
                      cssClass: 'apexcharts-xaxis-label',
                  },
                 },
              },
              yaxis: {
                tooltip: {
                  enabled: true
                },
                labels: {
                  show: true,
                  align: 'right',
                  minWidth: 0,
                  maxWidth: 160,
                  style: {
                      colors: '#FFF',
                      fontSize: '12px',
                      fontFamily: 'Helvetica, Arial, sans-serif',
                      fontWeight: 400,
                      cssClass: 'apexcharts-yaxis-label',
                  },
                 },
                
              }
            },
    }

    this.options = {
        place: "tr",
        message: <div className="text-right">{this.state.msg}</div>,
        type: "success",
        icon: "tim-icons icon-bell-55",
        autoDismiss: 10
     };
  }

    myFunc(){
        this.refs.notify.notificationAlert(this.options);
    }

  getData = async (id) => {

      try {
           let response = await fetch(
              `/api/candles`
          );

          let result = await response.json();
          let a = []
          for (let i=0;i<result.length;i++){

            let b = {
                  x: Date.parse(result[i]["openTime"]),
                  y: [result[i]["open"],result[i]["high"],result[i]["low"],result[i]["close"]]
                }
            a.push(b)
          }

          let c = [{
              data: a
            }]
          this.setState(
              prevState => (
                  {
                      series: c
                  }
              )
          );
      }
    catch(e){
          console.log(e)

     }
     console.log(this.state)
  }
  getKafka= async () => {
      let response = await fetch(
          `/test/data`
      );
      let result = await response.json();
      let a = result.slice(this.state.changes.length, result.length)
      console.log(result);

      this.state.changes = result;
      if (a.length != result.length) {

      for (let i = 0; i < a.length; i++) {
              this.options.message =<div className="text-right">NEW {a[i].split("EUR")[0]} price {a[i].split(",")[1]}</div>;
              this.myFunc()
          }
        }
      // if (this.options.message !== result[result.length-1]){
      //     this.options.message = result[result.length-1];
      // console.log(result)
      // this.myFunc()
      // this.setState(
      //     prevState => (
      //         {}
      //     )
       //);
     // }
  }
   componentDidMount() {
    this.getData(this.state.curent_page);
    this.timer = setInterval(() => this.getData(this.state.curent_page), 30000)
    this.timer = setInterval(() => this.getKafka(), 1000)
  }

  componentWillUnmount(){
    clearInterval(this.timer)
    this.timer = null
  }
  onDismiss(){
    this.setState({visible: !this.state.visible})
}
  render(){
  return (
    <>
      <div className="content">
          <Notify ref="notify" />
        <Row>
          <Col xs="12">
            <Card className="card-chart">
              <CardHeader>
                <Row>
                  <Col className="text-left" sm="6">
                    <h5 className="card-category"></h5>
                    <CardTitle tag="h2">BTC/EUR</CardTitle>
                  </Col>
                  <Col sm="6">

                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
                <div className="chart_div">
                  <Chart
                    options={this.state.options}
                    series={this.state.series}
                    type="candlestick"
                    height="500"
                  />
                </div>
              </CardBody>
            </Card>
          </Col>
        </Row>
        {/*<Row>*/}
        {/*  <Col lg="4">*/}
        {/*    <Card className="card-chart">*/}
        {/*      <CardHeader>*/}
        {/*        <h5 className="card-category">Total Shipments</h5>*/}
        {/*        <CardTitle tag="h3">*/}
        {/*          <i className="tim-icons icon-bell-55 text-info" /> 763,215*/}
        {/*        </CardTitle>*/}
        {/*      </CardHeader>*/}
        {/*      <CardBody>*/}
        {/*        <div className="chart-area">*/}
        {/*          <Line*/}
        {/*            data={chartExample2.data}*/}
        {/*            options={chartExample2.options}*/}
        {/*          />*/}
        {/*        </div>*/}
        {/*      </CardBody>*/}
        {/*    </Card>*/}
        {/*  </Col>*/}
        {/*  <Col lg="4">*/}
        {/*    <Card className="card-chart">*/}
        {/*      <CardHeader>*/}
        {/*        <h5 className="card-category">Daily Sales</h5>*/}
        {/*        <CardTitle tag="h3">*/}
        {/*          <i className="tim-icons icon-delivery-fast text-primary" />{" "}*/}
        {/*          3,500???*/}
        {/*        </CardTitle>*/}
        {/*      </CardHeader>*/}
        {/*      <CardBody>*/}
        {/*        <div className="chart-area">*/}
        {/*          <Bar*/}
        {/*            data={chartExample3.data}*/}
        {/*            options={chartExample3.options}*/}
        {/*          />*/}
        {/*        </div>*/}
        {/*      </CardBody>*/}
        {/*    </Card>*/}
        {/*  </Col>*/}
        {/*  <Col lg="4">*/}
        {/*    <Card className="card-chart">*/}
        {/*      <CardHeader>*/}
        {/*        <h5 className="card-category">Completed </h5>*/}
        {/*        <CardTitle tag="h3">*/}
        {/*          <i className="tim-icons icon-send text-success" /> 12,100K*/}
        {/*        </CardTitle>*/}
        {/*      </CardHeader>*/}
        {/*      <CardBody>*/}
        {/*        <div className="chart-area">*/}
        {/*          <Line*/}
        {/*            data={chartExample4.data}*/}
        {/*            options={chartExample4.options}*/}
        {/*          />*/}
        {/*        </div>*/}
        {/*      </CardBody>*/}
        {/*    </Card>*/}
        {/*  </Col>*/}
        {/*</Row>*/}
        {/*<Row>*/}
        {/*  <Col lg="6" md="12">*/}
        {/*    <Card className="card-tasks">*/}
        {/*      <CardHeader>*/}
        {/*        <h6 className="title d-inline">Tasks(5)</h6>*/}
        {/*        <p className="card-category d-inline"> today</p>*/}
        {/*        <UncontrolledDropdown>*/}
        {/*          <DropdownToggle*/}
        {/*            caret*/}
        {/*            className="btn-icon"*/}
        {/*            color="link"*/}
        {/*            data-toggle="dropdown"*/}
        {/*            type="button"*/}
        {/*          >*/}
        {/*            <i className="tim-icons icon-settings-gear-63" />*/}
        {/*          </DropdownToggle>*/}
        {/*          <DropdownMenu aria-labelledby="dropdownMenuLink" right>*/}
        {/*            <DropdownItem*/}
        {/*              href="#pablo"*/}
        {/*              onClick={(e) => e.preventDefault()}*/}
        {/*            >*/}
        {/*              Action*/}
        {/*            </DropdownItem>*/}
        {/*            <DropdownItem*/}
        {/*              href="#pablo"*/}
        {/*              onClick={(e) => e.preventDefault()}*/}
        {/*            >*/}
        {/*              Another action*/}
        {/*            </DropdownItem>*/}
        {/*            <DropdownItem*/}
        {/*              href="#pablo"*/}
        {/*              onClick={(e) => e.preventDefault()}*/}
        {/*            >*/}
        {/*              Something else*/}
        {/*            </DropdownItem>*/}
        {/*          </DropdownMenu>*/}
        {/*        </UncontrolledDropdown>*/}
        {/*      </CardHeader>*/}
        {/*      <CardBody>*/}
        {/*        <div className="table-full-width table-responsive">*/}
        {/*          <Table>*/}
        {/*            <tbody>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input defaultValue="" type="checkbox" />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">Update the Documentation</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    Dwuamish Head, Seattle, WA 8:47 AM*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip636901683"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip636901683"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input*/}
        {/*                        defaultChecked*/}
        {/*                        defaultValue=""*/}
        {/*                        type="checkbox"*/}
        {/*                      />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">GDPR Compliance</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    The GDPR is a regulation that requires businesses to*/}
        {/*                    protect the personal data and privacy of Europe*/}
        {/*                    citizens for transactions that occur within EU*/}
        {/*                    member states.*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip457194718"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip457194718"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input defaultValue="" type="checkbox" />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">Solve the issues</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    Fifty percent of all respondents said they would be*/}
        {/*                    more likely to shop at a company*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip362404923"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip362404923"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input defaultValue="" type="checkbox" />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">Release v2.0.0</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    Ra Ave SW, Seattle, WA 98116, SUA 11:19 AM*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip818217463"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip818217463"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input defaultValue="" type="checkbox" />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">Export the processed files</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    The report also shows that consumers will not easily*/}
        {/*                    forgive a company once a breach exposing their*/}
        {/*                    personal data occurs.*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip831835125"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip831835125"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*              <tr>*/}
        {/*                <td>*/}
        {/*                  <FormGroup check>*/}
        {/*                    <Label check>*/}
        {/*                      <Input defaultValue="" type="checkbox" />*/}
        {/*                      <span className="form-check-sign">*/}
        {/*                        <span className="check" />*/}
        {/*                      </span>*/}
        {/*                    </Label>*/}
        {/*                  </FormGroup>*/}
        {/*                </td>*/}
        {/*                <td>*/}
        {/*                  <p className="title">Arival at export process</p>*/}
        {/*                  <p className="text-muted">*/}
        {/*                    Capitol Hill, Seattle, WA 12:34 AM*/}
        {/*                  </p>*/}
        {/*                </td>*/}
        {/*                <td className="td-actions text-right">*/}
        {/*                  <Button*/}
        {/*                    color="link"*/}
        {/*                    id="tooltip217595172"*/}
        {/*                    title=""*/}
        {/*                    type="button"*/}
        {/*                  >*/}
        {/*                    <i className="tim-icons icon-pencil" />*/}
        {/*                  </Button>*/}
        {/*                  <UncontrolledTooltip*/}
        {/*                    delay={0}*/}
        {/*                    target="tooltip217595172"*/}
        {/*                    placement="right"*/}
        {/*                  >*/}
        {/*                    Edit Task*/}
        {/*                  </UncontrolledTooltip>*/}
        {/*                </td>*/}
        {/*              </tr>*/}
        {/*            </tbody>*/}
        {/*          </Table>*/}
        {/*        </div>*/}
        {/*      </CardBody>*/}
        {/*    </Card>*/}
        {/*  </Col>*/}
        {/*  <Col lg="6" md="12">*/}
        {/*    <Card>*/}
        {/*      <CardHeader>*/}
        {/*        <CardTitle tag="h4">Simple Table</CardTitle>*/}
        {/*      </CardHeader>*/}
        {/*      <CardBody>*/}
        {/*        <Table className="tablesorter" responsive>*/}
        {/*          <thead className="text-primary">*/}
        {/*            <tr>*/}
        {/*              <th>Name</th>*/}
        {/*              <th>Country</th>*/}
        {/*              <th>City</th>*/}
        {/*              <th className="text-center">Salary</th>*/}
        {/*            </tr>*/}
        {/*          </thead>*/}
        {/*          <tbody>*/}
        {/*            <tr>*/}
        {/*              <td>Dakota Rice</td>*/}
        {/*              <td>Niger</td>*/}
        {/*              <td>Oud-Turnhout</td>*/}
        {/*              <td className="text-center">$36,738</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Minerva Hooper</td>*/}
        {/*              <td>Cura??ao</td>*/}
        {/*              <td>Sinaai-Waas</td>*/}
        {/*              <td className="text-center">$23,789</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Sage Rodriguez</td>*/}
        {/*              <td>Netherlands</td>*/}
        {/*              <td>Baileux</td>*/}
        {/*              <td className="text-center">$56,142</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Philip Chaney</td>*/}
        {/*              <td>Korea, South</td>*/}
        {/*              <td>Overland Park</td>*/}
        {/*              <td className="text-center">$38,735</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Doris Greene</td>*/}
        {/*              <td>Malawi</td>*/}
        {/*              <td>Feldkirchen in K??rnten</td>*/}
        {/*              <td className="text-center">$63,542</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Mason Porter</td>*/}
        {/*              <td>Chile</td>*/}
        {/*              <td>Gloucester</td>*/}
        {/*              <td className="text-center">$78,615</td>*/}
        {/*            </tr>*/}
        {/*            <tr>*/}
        {/*              <td>Jon Porter</td>*/}
        {/*              <td>Portugal</td>*/}
        {/*              <td>Gloucester</td>*/}
        {/*              <td className="text-center">$98,615</td>*/}
        {/*            </tr>*/}
        {/*          </tbody>*/}
        {/*        </Table>*/}
        {/*      </CardBody>*/}
        {/*    </Card>*/}
        {/*  </Col>*/}
        {/*</Row>*/}
      </div>
    </>
  );
}
}
export default Dashboard;
