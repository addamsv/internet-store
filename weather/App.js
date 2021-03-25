import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import * as Location from 'expo-location';
import Loading from './Loading';

class App extends React.Component {
  state = {
    isLoading: true,
  };

  async getLocation() {
    try {
      await Location.requestPermissionsAsync();
      const location = await Location.getCurrentPositionAsync({});
      return { latitude: location.coords.latitude, longitude: location.coords.longitude };
    } catch(error) {
      return new Promise((resolve) => {
        resolve({latitude: false, longitude: false });
      });
    }
  }

  componentDidMount() {
    this.getLocation().then((location) => {
      if (location.latitude && location.longitude) {
        this.setState({ isLoading: false });
        console.log(location.latitude, location.longitude);
      } else {
        Alert.alert('Cant get Location');
      }
    });
  }

  render() {
    return (
      this.state.isLoading ? <Loading /> : null
    );
  }
}

export default App;

/* 
export default function App() {
  return (
    <Loading />
  );
}
*/

/* 
export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.containerUp}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <View style={styles.containerDown}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: '#f00',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerUp: {
    flex: 1,
    backgroundColor: '#fff',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerDown: {
    flex: 3,
    backgroundColor: '#ccc',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  text: {
    color: '#aaa',
    fontSize: 24,
    width: '80%',
  },
}); */
