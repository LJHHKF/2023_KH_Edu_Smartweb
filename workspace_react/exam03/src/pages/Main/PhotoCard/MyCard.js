import { CardBody, CardTitle, CardSubtitle, CardText, Card, Button } from 'reactstrap';

function MyCard(props) {

  console.log(props);
  const {random} = props;
  console.log(random);

  return (
    <Card className="p-1">
      <img alt="Sample" src={`https://picsum.photos/300/200?random=${random}`} className="p-1" />
      <CardBody className="p-1">
        <CardTitle tag="h5">Card title</CardTitle>
        <CardSubtitle className="mb-2 text-muted" tag="h6">
          Card subtitle
        </CardSubtitle>
        <CardText>
        </CardText>
        <Button>Button</Button>
      </CardBody>
    </Card>
  );
};

export default MyCard;
