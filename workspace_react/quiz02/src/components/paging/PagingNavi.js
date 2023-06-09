import { Fragment } from "react";

function PagingNavi(){
    const data = [1,2,3,4];

    const result = data.map((e, index)=>{
        return (
            <Fragment key={index}>{e}</Fragment>
        );
    });

    return(
        <div>
            {result}
        </div>
    );
};

export default PagingNavi;