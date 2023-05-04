import React from 'react';
import {Button} from '@mui/material';

const ButtonComponent = ({className = '', ...props}) => {
  return (
    <Button
      className={`${className} bg-button hover:bg-button-hover text-button-text font-bold py-2 px-4 border border-button-border rounded`}
      {...props}
    />
  );
};
export default ButtonComponent;
