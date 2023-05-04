import * as React from 'react';
import {
  AppBar,
  Toolbar,
  IconButton,
  Typography,
  Grid,
  TextField,
  Select,
  MenuItem,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
} from '@mui/material';
import { Add } from '@mui/icons-material';
import {useState} from "react";
import {CustomDialog} from "./CustomDialog";
import {AddPracticeDialog} from "./AddPracticeDialog";

function BestPractices() {
  const [cardDialogOpen, setCardDialogOpen] = useState(false);

  const handleCardDialogOpen = () => {
    setCardDialogOpen(true);
  }

  const handleCardDialogClose = () => {
    setCardDialogOpen(false);
  }

  function dialogContent () {
    return <AddPracticeDialog />
  }

  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" color="inherit" aria-label="menu">
            {/* Логотип */}
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Наилучшие практики
          </Typography>
          <Button color="inherit">Войти</Button>
        </Toolbar>
      </AppBar>
      <Grid container sx={{ marginTop: 2 }}>
        <Grid item xs={12} sm={4} md={3}>
          {/* Фильтры */}
          <Typography variant="h6" gutterBottom>Фильтры</Typography>
          <TextField label="Поиск" fullWidth margin="normal" />
          <Select displayEmpty value="" fullWidth margin="normal">
            <MenuItem value="">Категория</MenuItem>
            {/* Опции для категорий */}
          </Select>
          <Select displayEmpty value="" fullWidth margin="normal">
            <MenuItem value="">Команда</MenuItem>
            {/* Опции для команд */}
          </Select>
        </Grid>
        <Grid item xs={12} sm={8} md={9}>
          {/* Таблица */}
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Наилучшие практики</TableCell>
                <TableCell>Категория</TableCell>
                <TableCell>Команда</TableCell>
                <TableCell>Количество голосов</TableCell>
                <TableCell>Автор</TableCell>
                <TableCell>Голосовать</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {/* Строки таблицы */}
            </TableBody>
          </Table>
          {/* Кнопка добавления новой практики */}
          <Button
              variant="contained" sx={{ mt: 2 }}
              startIcon={<Add />}
              onClick={handleCardDialogOpen}
          >
            Добавить новую практику
          </Button>
        </Grid>
      </Grid>
      <CustomDialog
          open={cardDialogOpen}
          onClose={handleCardDialogClose}
          content={dialogContent()}
      />
    </div>
  );
}

export default BestPractices;