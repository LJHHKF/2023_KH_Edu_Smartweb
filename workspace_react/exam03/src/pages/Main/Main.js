import { Col, Row } from 'reactstrap';
import MyCard from './PhotoCard/MyCard';

function Main() {
  return (
    <Row>
      <Col xs="12" sm="9" className="py-1">
        <Row>
          <Col xs="12" sm="6" md="4" className="py-1">
            <MyCard random="1"></MyCard>
          </Col>
          <Col xs="12" sm="6" md="4" className="py-1">
            <MyCard random="2"></MyCard>
          </Col>
          <Col xs="12" sm="6" md="4" className="py-1">
            <MyCard random="3"></MyCard>
          </Col>
          <Col xs="12" sm="6" md="4" className="py-1">
            <MyCard random="4"></MyCard>
          </Col>
        </Row>
      </Col>
      <Col xs="12" sm="3"></Col>
    </Row>
  );
}

export default Main;
